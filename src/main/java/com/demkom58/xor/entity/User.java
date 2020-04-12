package com.demkom58.xor.entity;

import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Login cannot be empty")
    private String login;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @Column(name = "uname")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Surname cannot be empty")
    private String surname;
    private String patronymic;

    private String status;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Message> sentMessages;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Message> receivedMessages;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Post> sentPosts;

    @OneToMany(mappedBy = "wallOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Post> wallPosts;

    User() {

    }

    User(String login) {
        this.login = login.toLowerCase();
    }

    public User(String login, String password, String email, String name, String surname, List<Role> roles) {
        this(login);
        if (login.isEmpty())
            throw new IllegalArgumentException();

        this.password = password;
        if (password.isEmpty())
            throw new IllegalArgumentException();

        this.email = email;
        if (email.isEmpty() || email.contains(" "))
            throw new IllegalArgumentException();

        this.name = name;
        if (name.isEmpty())
            throw new IllegalArgumentException();

        this.surname = surname;
        if (surname.isEmpty())
            throw new IllegalArgumentException();

        this.roles = new HashSet<>(roles);
        if (roles.isEmpty())
            throw new IllegalArgumentException();

    }

    public Set<Post> getWallPosts() {
        return wallPosts;
    }

    public void setWallPosts(Set<Post> wallPosts) {
        this.wallPosts = wallPosts;
    }

    public Set<Post> getSentPosts() {
        return sentPosts;
    }

    public void setSentPosts(Set<Post> sentPosts) {
        this.sentPosts = sentPosts;
    }

    public Set<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(Set<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public Set<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(Set<Message> messages) {
        this.sentMessages = messages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Nullable
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(@Nullable String patronymic) {
        this.patronymic = patronymic;
    }

    public String getFullName() {
        final String fullname = getName() + " " + getSurname();
        return getPatronymic() == null ? fullname : fullname + " " + getPatronymic();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
