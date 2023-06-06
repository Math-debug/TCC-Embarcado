package unip.tcc.config;

import java.io.Serializable;

public class XmlConfig implements Serializable{
    
    private Boolean enableMonofasico;
    private Boolean enableBifasico;
    private Boolean enableTrifasico;
    private Boolean enableTerra;
    private Boolean enableNeutro;
    
    public Boolean getEnableMonofasico() {
        return enableMonofasico;
    }
    public void setEnableMonofasico(Boolean enableMonofasico) {
        this.enableMonofasico = enableMonofasico;
    }
    public Boolean getEnableBifasico() {
        return enableBifasico;
    }
    public void setEnableBifasico(Boolean enableBifasico) {
        this.enableBifasico = enableBifasico;
    }
    public Boolean getEnableTrifasico() {
        return enableTrifasico;
    }
    public void setEnableTrifasico(Boolean enableTrifasico) {
        this.enableTrifasico = enableTrifasico;
    }
    public Boolean getEnableTerra() {
        return enableTerra;
    }
    public void setEnableTerra(Boolean enableTerra) {
        this.enableTerra = enableTerra;
    }
    public Boolean getEnableNeutro() {
        return enableNeutro;
    }
    public void setEnableNeutro(Boolean enableNeutro) {
        this.enableNeutro = enableNeutro;
    }
    
    
}
