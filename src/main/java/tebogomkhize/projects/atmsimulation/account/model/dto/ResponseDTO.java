package tebogomkhize.projects.atmsimulation.account.model.dto;

import java.util.List;

public class ResponseDTO {
    String outcome;
    String message;
    List<Object> data;
    // outcome, message, data


    public ResponseDTO(String outcome, String message, List<Object> data) {
        this.data = data;
        this.outcome = outcome;
        this.message = message;
    }

    // Getters and Setters.
    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
