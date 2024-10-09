package utn.frc.bka;

public class Client {
    private Integer clientId;

    private String name;

    public Client(Integer clientId, String name) {
        this.clientId = clientId;
        this.name = name;

    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
