package com.scheduledlinkmanager.rules_service.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long daysCount; // Будет не nullable потому, что размерность типа больше чем проживет наша цивилизация
    private int weekDays; // Битовая маска - красиво
    private Long routeCounter; // Вместо отдельной таблицы

    // Связь таблиц
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "code_id")
    private Code code;

    // Методы для работы с битовой маской
    public boolean isDayEnabled(int dayOfWeek) {
        return (weekDays & (1 << dayOfWeek)) != 0;
    }
    public void setDayEnabled(int dayOfWeek, boolean enabled) {
        if (enabled) {
            weekDays |= (1 << dayOfWeek);
        } else {
            weekDays &= ~(1 << dayOfWeek);
        }
    }
}
