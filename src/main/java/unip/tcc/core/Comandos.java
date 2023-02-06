package unip.tcc.core;

import javax.persistence.Column;

public class Comandos {
	private String comand;
	private String equipmentId;
	private String ipJMS;
	private String SSID;
	private String senhaRede;
	private String isEnabledSystem;
	private String tipoRede;
	private String neutroAtivo;
	private String terraAtivo;
    private String subrede;
    private String gateway;
	
	public String getComand() {
		return comand;
	}
	public void setComand(String comand) {
		this.comand = comand;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		int x = equipmentId.length();
		int y = 3-x;
		StringBuilder mensagem = new StringBuilder();
		for(int i=0;i < y;i++ ) {
			mensagem.append("0");
		}
		this.equipmentId = mensagem.toString() + equipmentId;
	}
	public String getIpJMS() {
		return ipJMS;
	}
	public void setIpJMS(String ipJMS) {
		int x = ipJMS.length();
		int y = 16-x;
		StringBuilder mensagem = new StringBuilder();
		for(int i=0;i < y;i++ ) {
			mensagem.append("0");
		}
		this.ipJMS =  mensagem.toString() + ipJMS;
	}
	public String getSSID() {
		return SSID;
	}
	public void setSSID(String sSID) {
		int x = sSID.length();
		int y = 20-x;
		StringBuilder mensagem = new StringBuilder();
		for(int i=0;i < y;i++ ) {
			mensagem.append("0");
		}
		SSID = mensagem.toString() + sSID;
	}
	public String getSenhaRede() {
		return senhaRede;
	}
	public void setSenhaRede(String senhaRede) {
		int x = senhaRede.length();
		int y = 20-x;
		StringBuilder mensagem = new StringBuilder();
		for(int i=0;i < y;i++ ) {
			mensagem.append("0");
		}
		this.senhaRede =  mensagem.toString() + senhaRede;
	}
	public String getIsEnabledSystem() {
		return isEnabledSystem;
	}
	public void setIsEnabledSystem(String isEnabledSystem) {
		this.isEnabledSystem = isEnabledSystem;
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
    public void setGateway(String gateway) {
        int x = gateway.length();
        int y = 16-x;
        StringBuilder mensagem = new StringBuilder();
        for(int i=0;i < y;i++ ) {
            mensagem.append("0");
        }
        this.gateway =  mensagem.toString() + gateway;
    }
    public String getSubrede() {
        return subrede;
    }
    public void setSubrede(String subrede) {
        int x = subrede.length();
        int y = 16-x;
        StringBuilder mensagem = new StringBuilder();
        for(int i=0;i < y;i++ ) {
            mensagem.append("0");
        }
        this.subrede =  mensagem.toString() + subrede;
    }
    public String getGateway() {
        return gateway;
    }
}
