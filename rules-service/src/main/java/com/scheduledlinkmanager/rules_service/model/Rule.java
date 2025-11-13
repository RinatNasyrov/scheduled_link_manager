package com.scheduledlinkmanager.rules_service.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long daysCount; // Будет не nullable потому, что размерность типа больше чем проживет наша цивилизация
    private Integer weekDays; // Битовая маска - красиво
    private Long routeCounter; // Вместо отдельной таблицы
    private String routeURL;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate; // Потом буду по нему сортировать, при поиске первого подходящего правила
    

    // Связь таблиц
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "code_id")
    private Code code;

    // Методы для работы с битовой маской
    public boolean isDayEnabled(int dayOfWeek) {
        return (weekDays != null) && (weekDays & (1 << dayOfWeek)) != 0;
    }
    public void setDayEnabled(int dayOfWeek, boolean enabled) {
        if (weekDays == null)
            weekDays = 0;

        if (enabled) {
            weekDays |= (1 << dayOfWeek);
        } else {
            weekDays &= ~(1 << dayOfWeek);
        }
    }

    @PrePersist
    @PreUpdate
    public void validateDateTimeData() {
        if ((endTime != null && startTime != null && endTime.isBefore(startTime)) || 
            (endDate != null && startDate != null && endDate.isBefore(startDate))) {
            throw new IllegalStateException("Неверно заполнены поля дат или времени");
        }
    }
}
