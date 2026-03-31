package com.sayed.pos.common.util;

import com.sayed.pos.common.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

@Component
@Getter
@Setter
public class DateUtils {
    public static Instant toStartOfDay(LocalDate date) {
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant();
    }

    public static Instant toEndOfDay(LocalDate date) {
        return date.atTime(LocalTime.MAX)
                .atZone(ZoneId.systemDefault())
                .toInstant();
    }

    public static void validateDateRange(LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            throw new BadRequestException("Date range is required");
        }
        if (from.isAfter(to)) {
            throw new BadRequestException("Invalid date range");
        }
    }

    public static LocalDate resolveFrom(LocalDate from) {
        if (from != null) return from;
        return LocalDate.now().withDayOfMonth(1);
    }

    public static LocalDate resolveTo(LocalDate to) {
        if (to != null) return to;
        return LocalDate.now();
    }


    public static Pair<Instant, Instant> resolveDateRange(LocalDate from, LocalDate to) {

        LocalDate resolvedFrom = (from != null)
                ? from
                : LocalDate.now().withDayOfMonth(1);

        LocalDate resolvedTo = (to != null)
                ? to
                : LocalDate.now();

        DateUtils.validateDateRange(resolvedFrom, resolvedTo);

        return Pair.of(
                DateUtils.toStartOfDay(resolvedFrom),
                DateUtils.toEndOfDay(resolvedTo)
        );
    }
}
