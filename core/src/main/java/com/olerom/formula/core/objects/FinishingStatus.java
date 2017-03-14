package com.olerom.formula.core.objects;

/**
 * Date: 14.03.17
 *
 * @author olerom
 */
public class FinishingStatus {
    private int statusId;
    private int count;
    private String status;

    public FinishingStatus(int statusId, int count, String status) {
        this.statusId = statusId;
        this.count = count;
        this.status = status;
    }

    public int getStatusId() {
        return statusId;
    }

    public int getCount() {
        return count;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "FinishingStatus{" +
                "statusId=" + statusId +
                ", count=" + count +
                ", status='" + status + '\'' +
                '}';
    }
}
