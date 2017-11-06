package com.example.demo.Controller;

import com.example.demo.Entity.Token;
import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import com.example.demo.dto.WrapperDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @Mock
    private UserService userService;
    @InjectMocks
    UserController sut;

    @Test
    public void getAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(Collections.emptyList());
        Collection<User> listUsers = sut.getAllUsers();
        verify(userService).getAllUsers();
        assertThat(listUsers.size(), is(0));
    }

    @Test
    public void register() throws Exception {
        User user = mock(User.class);
        user.setEmail(anyString());
        user.setPassword(anyString());

        doNothing().when(userService).insertUser(any(User.class));

        WrapperDTO dto = sut.register(user);

        verify(userService).insertUser(any());
        assertTrue(dto.getSuccess());
    }

    @Test
    public void confirm() throws Exception {
        User user = mock(User.class);
        user.setEmail(anyString());
        user.setPassword(anyString());
        Token token = mock(Token.class);
        token.setToken(anyString());

        when(userService.getUserByToken(anyString())).thenReturn(user);

        WrapperDTO dto = sut.confirm(token);

        verify(userService).getUserByToken(anyString());
        assertTrue(dto.getSuccess());
    }

    @Test
    public void login() throws Exception {
        User user = mock(User.class);
        user.setEmail(anyString());
        user.setPassword(anyString());

        when(userService.login(user)).thenReturn(user);

        WrapperDTO dto = sut.login(user);

        verify(userService).login(any());
        assertTrue(dto.getSuccess());
    }

    @Test
    public void handleException() throws Exception {
        Exception e = new Exception();

        WrapperDTO dto = sut.handleException(e);

        assertFalse(dto.getSuccess());
    }

}