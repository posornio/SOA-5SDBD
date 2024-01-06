package fr.insa.mas.BeneficiareManager.Model;

public class DbInfo {
    private String url;
    private String user;
    private String password;

    public DbInfo() {
    }

    public DbInfo(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public String toString() {
        return "DbInfo{" +
                "url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
