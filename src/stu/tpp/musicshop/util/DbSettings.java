package stu.tpp.musicshop.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "settings")
public class DbSettings {
    private String url;
    private String user;
    private String password;

    public DbSettings() {
        this.url = "";
        this.user = "";
        this.password = "";
    }

    @XmlElement(name = "url")
    public String getUrl() {
        return url;
    }

    @XmlElement(name = "user")
    public String getUser() {
        return user;
    }

    @XmlElement(name = "password")
    public String getPassword() {
        return password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static DbSettings getSettingsFromFile() {
        try {
            JAXBContext context = JAXBContext.newInstance(DbSettings.class);
            Unmarshaller um = context.createUnmarshaller();

            return (DbSettings) um.unmarshal(DbSettings.class.getResource("db_settings.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
            return new DbSettings();
        }
    }
}
