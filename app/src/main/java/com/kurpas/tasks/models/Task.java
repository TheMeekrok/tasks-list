package com.kurpas.tasks.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "task")
public class Task implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "text")
    public String text;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    @ColumnInfo(name = "is_done")
    public boolean isDone;

    @ColumnInfo(name = "list_id")
    public int listId;

    public Task() {}

    protected Task(Parcel in) {
        id = in.readInt();
        text = in.readString();
        timestamp = in.readLong();
        isDone = in.readByte() != 0;
        listId = in.readInt();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(text);
        parcel.writeLong(timestamp);
        parcel.writeByte((byte) (isDone ? 1 : 0));
        parcel.writeInt(listId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && timestamp == task.timestamp && isDone == task.isDone && listId == task.listId && Objects.equals(text, task.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, timestamp, isDone, listId);
    }
}
