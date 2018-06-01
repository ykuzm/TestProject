package system.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.exceptions.NotFoundInDatabaseException;
import system.model.Passenger;
import system.service.PassengerService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PassengerService passengerService;

    public PassengerService getPassengerService() {
        return passengerService;
    }

    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException
    {
        Passenger passenger = passengerService.getPassengerByLogin(login);
        if(passenger == null){
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(passenger.getLogin(), "{noop}" +
                passenger.getPassword(), true, true, true,
                true, getGrantedAuthorities(passenger));
    }


    private List<GrantedAuthority> getGrantedAuthorities(Passenger passenger){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + (passenger.isAdmin() ? "ADMIN" : "USER")));
        return authorities;
    }

}
