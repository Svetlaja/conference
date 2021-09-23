package nekrasova.svetlana.conference.entityDto;

import nekrasova.svetlana.conference.entity.User;

public class UserDto {
    private long userId;
    private String userName;
    private String password;
    private String passwordConfirm;
    private String roleName;

    public UserDto() {
    }

    public UserDto(long userId, String userName, String roleName) {
        this.userId = userId;
        this.userName = userName;
        this.roleName = roleName;
    }

    public UserDto(long userId, String userName, String password, String passwordConfirm, String roleName) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.roleName = roleName;
    }

    public UserDto(String userName, String password, String passwordConfirm, String roleName) {
        this.userName = userName;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.roleName = roleName;
    }

    public UserDto(String userName, String password, String passwordConfirm) {
        this.userName = userName;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public static UserDto fromUser(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getRole().name());
    }
}
