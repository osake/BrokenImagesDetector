package ru.fdman.bidfx.process.processes.processor.result;


import ru.fdman.bidfx.Status;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BytesProcessResult implements Comparable<BytesProcessResult>{
    private final Path path;

    public boolean isLeaf() {
        return isLeaf;
    }

    private boolean isLeaf;

    private String resultName;
    private String description;

    private String details;
    private Status status = Status.FOLDER;
    private List<BytesProcessResult> childResults = new ArrayList<>();

    public BytesProcessResult(String path) {
        this.isLeaf = false;
        this.path = new File(path).toPath();
    }

    public Path getPath() {
        return path;
    }

    public BytesProcessResult(Path path, String resultName) {
        this.isLeaf = true;
        this.path = path;
        this.resultName = resultName;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (this.status.getPriority() < status.getPriority()) {
            this.status = status;
        }
    }

    public void addChildResult(BytesProcessResult childProcessResult) {
        childResults.add(childProcessResult);
        setDescription(getDescription() + "\n" + childProcessResult.resultName  + ": "+childProcessResult.getDescription());
        setDetails(getDetails() + "\n" + childProcessResult.resultName  + ": "+childProcessResult.getDetails());
        setStatus(childProcessResult.getStatus());
    }


    //@Override
    public String toString() {
        return "BytesProcessResult{" +
                "path=" + path +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", childResults=" + childResults +
                '}';
    }


    public String toString2() {
        return                 "path=" + path;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public int compareTo(BytesProcessResult o) {
        return getPath().compareTo(o.getPath());
    }
}
