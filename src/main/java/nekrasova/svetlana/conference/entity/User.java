package nekrasova.svetlana.conference.entity;

import nekrasova.svetlana.conference.entity.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @Size(min = 2, message = "Не меньше 2 знаков")
    private String userName;
    @Size(min = 2, message = "Не меньше 2 знаков")
    private String password;
    @Transient
    private String passwordConfirm;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "varchar(32) default 'LISTENER'")
    private Role role;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "users_talks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "talk_id"))
    private Set<Talk> speakersTalks = new HashSet<>();


    public User() {
    }

    public User(String userName, String password, Role role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public void addTalk(Talk talk) {
        this.speakersTalks.add(talk);
        talk.getSpeakers().add(this);
    }

    public void removeTalk(Talk talk) {
        this.speakersTalks.remove(talk);
        talk.getSpeakers().remove(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        userName = userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<Role> col = new ArrayList<>();
        col.add(getRole());
        return col;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Talk> getSpeakersTalks() {
        return speakersTalks;
    }

    public void setSpeakersTalks(Set<Talk> speakersTalks) {
        this.speakersTalks = speakersTalks;
    }
}
