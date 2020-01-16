package rso.rsopix5.uploader.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;
import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("uploader-config")
public class ConfigProperties {
    @ConfigValue(watch = true)
    private String test;

    @ConfigValue(value = "ime", watch = true)
    private String ime;


    ConfigProperties() {
    }


    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
}
