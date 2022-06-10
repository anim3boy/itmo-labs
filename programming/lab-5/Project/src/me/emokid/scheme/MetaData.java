package me.emokid.scheme;

import me.emokid.json.JSONMap;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class MetaData {

    /**
     * {@link #MetaData}'s date field
     */

    private final LocalDateTime date;

    /**
     * {@link #MetaData}'s reserved constant key
     */

    public static final String metaDataKey = "meta-data-e0995c5d36cb24c67062678746922eb9";

    /**
     * Fill {@link #MetaData}'s fields automatically
     *
     */

    public MetaData() {
        this.date = LocalDateTime.now();
    }

    /**
     * Fill all {@link #MetaData}'s fields by JSONMap
     *
     * @param jsonMap is using to fill all fields
     * @throws NullPointerException if JSON map haven't date field or date field == {@code null}
     * @throws DateTimeParseException if date couldn't be parsed
     */

    public MetaData(JSONMap jsonMap) throws DateTimeParseException, NullPointerException {
        String date = jsonMap.getString("date");
        this.date = LocalDateTime.parse(date);
    }

    /**
     * @return date
     */

    public LocalDateTime getDate() {
        return date;
    }
}
