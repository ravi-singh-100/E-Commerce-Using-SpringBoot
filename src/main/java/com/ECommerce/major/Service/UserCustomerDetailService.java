package com.ECommerce.major.Service;

import com.ECommerce.major.Model.User;
import com.ECommerce.major.Model.UserCustomerDetail;
import com.ECommerce.major.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserCustomerDetailService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user= userRepo.findByEmail(email).get();
        UserCustomerDetail userCustomerDetail=new UserCustomerDetail(user);
       return userCustomerDetail;
    }
}
