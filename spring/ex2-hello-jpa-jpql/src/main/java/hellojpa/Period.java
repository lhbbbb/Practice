package hellojpa;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class Period {
    private LocalDateTime startDt;
    private LocalDateTime endDt;
    public Period() {
    }

    public Period(LocalDateTime startDt, LocalDateTime endDt) {
        this.startDt = startDt;
        this.endDt = endDt;
    }
}
