package unip.tcc.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_config")
public class Configuracoes implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="equipmentid")
    private String equipmentId;
    @Column(name="ipjms")
    private String ipJMS;
    @Column(name="SSID")
    private String SSID;
    @Column(name="password")
    private String senhaRede;
    @Column(name="type")
    private String tipoRede;
    @Column(name="neutro")
    private String neutroAtivo;
    @Column(name="ground")
    private String terraAtivo;
    
    public String getEquipmentId() {
        return equipmentId;
    }
    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }
    public String getIpJMS() {
        return ipJMS;
    }
    public void setIpJMS(String ipJMS) {
        this.ipJMS = ipJMS;
    }
    public String getSSID() {
        return SSID;
    }
    public void setSSID(String sSID) {
        SSID = sSID;
    }
    public String getSenhaRede() {
        return senhaRede;
    }
    public void setSenhaRede(String senhaRede) {
        this.senhaRede = senhaRede;
    }
    public String getTipoRede() {
        return tipoRede;
    }
    public void setTipoRede(String tipoRede) {
        this.tipoRede = tipoRede;
    }
    public String getNeutroAtivo() {
        return neutroAtivo;
    }
    public void setNeutroAtivo(String neutroAtivo) {
        this.neutroAtivo = neutroAtivo;
    }
    public String getTerraAtivo() {
        return terraAtivo;
    }
    public void setTerraAtivo(String terraAtivo) {
        this.terraAtivo = terraAtivo;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
}
