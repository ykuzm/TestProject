package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.additional.RoadAdd;
import system.additional.RoadCoordScheme;
import system.dao.RoadDao;
import system.dao.StationDao;
import system.exceptions.CantAddDataException;
import system.exceptions.NotFoundInDatabaseException;
import system.model.Road;
import system.model.Station;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoadService {

    @Autowired
    private RoadDao roadDao;

    @Autowired
    private StationService stationService;

    public RoadService() {
    }

    public RoadDao getRoadDao() {
        return roadDao;
    }

    public void setRoadDao(RoadDao roadDao) {
        this.roadDao = roadDao;
    }

    public StationService getStationService() {
        return stationService;
    }

    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    /**
     * Method for getting all roads from DB
     *
     * @return full list of roads from DB
     */
    public List<Road> getAllRoads(){ return roadDao.getAllRoads(); }

    /**
     * Method for getting road by id
     *
     * @param id id
     * @return Road with selected id, null if there is no Road in DB with this id
     */
    public Road getRoadById(int id) {return roadDao.getRoadById(id); }

    /**
     * Method for getting roads by station id
     *
     * @param stationId station id
     * @return roads with stations with selected id
     */
    public List<Road> getRoadByStationId(int stationId){ return roadDao.getRoadByStationId(stationId); }

    /**
     * Method for getting roads by two stations id
     *
     * @param stationId1 station 1 id
     * @param stationId2 station 2 id
     * @return road with stations with selected ids
     */
    public Road getRoadByStationId1AndStationId2(int stationId1, int stationId2) throws NotFoundInDatabaseException {
        Road road = roadDao.getRoadByStationId1AndStationId2(stationId1, stationId2);
        if (road == null) {
            throw new NotFoundInDatabaseException("There is no road between these stations");
        }
        return road;
    }

    /**
     * Method for adding Road to DB
     *
     * @param roadAdd roadAdd
     * @throws CantAddDataException if road already exists in DB
     */
    public void addRoad(RoadAdd roadAdd) throws CantAddDataException, NotFoundInDatabaseException {
        Station station1 = stationService.getStationByName(roadAdd.getStation1().getName());
        Station station2 = stationService.getStationByName(roadAdd.getStation2().getName());
        if (station1.equals(station2)) { // проверка на различие станций
            throw new CantAddDataException("Choose two different stations!");
        }
        double distance = Math.sqrt(Math.pow(station1.getCoordX()-station2.getCoordX(), 2) +
                Math.pow(station1.getCoordY()-station2.getCoordY(), 2));
        distance = Math.round(distance * 100) * 1.0 / 100; // вычисление расстояния между станциями
        Road road = createRoad(station1.getId(), station2.getId(), distance);
        if (roadDao.getAllRoads().contains(road)) { // проверка на существование дороги между станциями
            throw new CantAddDataException("Road from " + stationService.getStationById(road.getStationId1()).getName()
                    + " to " + stationService.getStationById(road.getStationId2()).getName() + " with distance "
                    + road.getDistance() + " already exists.");
        }
        roadDao.addRoad(road);
    }

    /**
     * Method for creating road from input data
     *
     * @param stationId1 station id 1
     * @param stationId2 station id 2
     * @param distance distance
     * @return road between two stations
     */
    public Road createRoad(int stationId1, int stationId2, double distance) {
        Road road = new Road();
        road.setStationId1(stationId1);
        road.setStationId2(stationId2);
        road.setDistance(distance);
        return road;
    }

    /**
     * Method for creating matrix of roads between stations
     *
     * @return matrix of roads between stations
     */
    public double[][] createSmegMatrix() {
        List<Station> stationList = stationService.getAllStations();
        int size = stationList.get(stationList.size()-1).getId() + 1;
        double[][] matrix = new double[size][size];
        for (int row = 0; row < matrix.length; row++) { // заполнение матрицы смежности нулями
            for (int column = 0; column < matrix[0].length; column++){
                matrix[row][column] = 0;
            }
        }
        for (int row = 1; row < matrix.length; row++) { // заполнение матрицы смежностями расстояниями из бд
            List<Road> roadList = roadDao.getRoadByStationId(row);
            for (Road road: roadList) {
                matrix[row][road.getStationId2()] = road.getDistance();
            }
        }
        return matrix;
    }

    /**
     * Dijkstra algorithm realization
     *
     * @param startId start station id
     * @param endId end station id
     * @return List of shortest ways
     */
    public List<Integer> dijkstra(int startId, int endId) throws NotFoundInDatabaseException {
        double matrix[][] = createSmegMatrix(); // матрица смежности
        int inf = Integer.MAX_VALUE / 2;
        int vNum = matrix.length; // количество вершин
        boolean[] used = new boolean [vNum]; // массив пометок
        double[] dist = new double[vNum]; // массив расстояний. dist[v] = минимальное_расстояние(start, v)
        int[] prev = new int[vNum]; // массив предыдущих оптимальных вершин
        List<Integer> trainWay = new ArrayList<Integer>(); // путь поезда по кратчайшему пути

        for (int i = 0; i < vNum; i++) { // устанавливаем расстояние до всех вершин inf
            dist[i] = inf;
        }

        for (int i = 0; i < vNum; i++) { // устанаавливаем все оптимальные вершины равными stationId
            prev[i] = 0;
        }

        dist[startId] = 0; // для начальной вершины положим расстояние 0

        for (;;) {
            int v = -1;
            for (int nv = 1; nv < vNum; nv++) { // перебираем вершины
                if (!used[nv] && dist[nv] < inf && (v == -1 || dist[v] > dist[nv])) { // выбираем самую близкую непомеченную вершину
                    v = nv;
                }
            }
            if (v == -1) break; // ближайшая вершина не найдена
            used[v] = true; // помечаем ее

            for (int nv = 1; nv < vNum; nv++) { // для всех непомеченных смежных
                if (!used[nv] && matrix[v][nv] != 0) { // улучшаем оценку расстояния
                    if (dist[nv] > dist[v] + matrix[v][nv]) {
                    dist[nv] = dist[v] + matrix[v][nv];
                    prev[nv] = v;
                    }
                }
            }
        }

        int index = endId;
        trainWay.add(endId);
        while (index != startId) { // получаем оптимальный путь по вершинам
            index = prev[index];
            trainWay.add(0, index);
            if (index == 0) { // проверка на наличие пути
                throw new NotFoundInDatabaseException("There is no way from " +
                        stationService.getStationById(startId).getName() + " to station " +
                        stationService.getStationById(endId).getName() + "! Check road scheme.");
            }
        }
        return trainWay;
    }

    /**
     * Method for getting all roads with their beginning and ending coordinates X, Y
     *
     * @return List with roads with their beginning and ending coordinates X, Y
     */
    public List<RoadCoordScheme> getRoadCoordScheme() {
        List<RoadCoordScheme> roadCoordSchemeList = new ArrayList<RoadCoordScheme>();
        List<Road> roadList = getAllRoads();
        for (Road road: roadList) {
            Station station1 = stationService.getStationById(road.getStationId1());
            Station station2 = stationService.getStationById(road.getStationId2());
            RoadCoordScheme roadCoordScheme = new RoadCoordScheme();
            roadCoordScheme.setCoordX1(station1.getCoordX());
            roadCoordScheme.setCoordY1(station1.getCoordY());
            roadCoordScheme.setCoordX2(station2.getCoordX());
            roadCoordScheme.setCoordY2(station2.getCoordY());
            roadCoordSchemeList.add(roadCoordScheme);
        }
        return roadCoordSchemeList;
    }

    /**
     * Method for getting the very left X coordinate from all stations
     *
     * @return very left X coordinate from all stations
     */
    public int getLeftCoordX() {
        List<Station> stationList = stationService.getAllStations();
        int coord = Integer.MAX_VALUE;
        for (Station station: stationList) {
            if (station.getCoordX() < coord) {
                coord = station.getCoordX();
            }
        }
        return coord;
    }

    /**
     * Method for getting the very right X coordinate from all stations
     *
     * @return very right X coordinate from all stations
     */
    public int getRightCoordX() {
        List<Station> stationList = stationService.getAllStations();
        int coord = Integer.MIN_VALUE;
        for (Station station: stationList) {
            if (station.getCoordX() > coord) {
                coord = station.getCoordX();
            }
        }
        return coord;
    }

    /**
     * Method for getting the very top Y coordinate from all stations
     *
     * @return very top Y coordinate from all stations
     */
    public int getTopCoordY() {
        List<Station> stationList = stationService.getAllStations();
        int coord = Integer.MAX_VALUE;
        for (Station station: stationList) {
            if (station.getCoordY() < coord) {
                coord = station.getCoordY();
            }
        }
        return coord;
    }

    /**
     * Method for getting the very botton Y coordinate from all stations
     *
     * @return very bottom Y coordinate from all stations
     */
    public int getBottomCoordY() {
        List<Station> stationList = stationService.getAllStations();
        int coord = Integer.MIN_VALUE;
        for (Station station: stationList) {
            if (station.getCoordY() > coord) {
                coord = station.getCoordY();
            }
        }
        return coord;
    }
}
