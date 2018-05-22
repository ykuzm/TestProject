package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.PassengerDao;
import system.dao.ScheduleDao;
import system.dao.TrainDao;
import system.model.Passenger;
import system.model.Schedule;
import system.model.Ticket;
import system.model.Train;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    public ScheduleService() { }

    public ScheduleDao getScheduleDao() { return scheduleDao; }

    public void setScheduleDao(ScheduleDao scheduleDao) { this.scheduleDao = scheduleDao; }

    public List<Schedule> getAllSchedule(){ return scheduleDao.getAllSchedule(); }

    public List<Schedule> getScheduleByTrainId(int trainId){ return scheduleDao.getScheduleByTrainId(trainId); }

    public List<Schedule> getScheduleByStationId(int stationId){ return scheduleDao.getScheduleByStationId(stationId); }

    public void addSchedule(Schedule schedule) {
        scheduleDao.addSchedule(schedule);
    }

}
