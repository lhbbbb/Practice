package hellojpa;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {
    private String createdBy;
    private LocalDateTime createdDt;
    private String modifiedBy;
    private LocalDateTime modifiedDt;

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreatedDt() {
        return createdDt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public LocalDateTime getModifiedDt() {
        return modifiedDt;
    }
}
