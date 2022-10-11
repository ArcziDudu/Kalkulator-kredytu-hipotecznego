package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TimePoint {
    private final LocalDate date;
    private final BigDecimal year;
    private final BigDecimal mont;

    public TimePoint(LocalDate date, BigDecimal year, BigDecimal mont) {
        this.date = date;
        this.year = year;
        this.mont = mont;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getYear() {
        return year;
    }

    public BigDecimal getMont() {
        return mont;
    }
}
