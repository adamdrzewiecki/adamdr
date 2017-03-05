package com.hp.model.domain;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Task {
    private int id;
    private String text;

    public Task(String text) {
        this.id = 0;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                Objects.equal(text, task.text);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, text);
    }
}
