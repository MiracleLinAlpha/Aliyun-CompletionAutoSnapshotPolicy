package entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE)

public class AutoSnapshotPolicyEty {
    private String Status;
    private String TimePoints;
    private int shared;
    private int VolumeNums;
    private String AutoSnapshotPolicyId;
    private int RetentionDays;
    private String RepeatWeekdays;
    private int DiskNums;
    private int CopiedSnapshotsRetentionDays;
    private int Department;
    private boolean EnableCrossRegionCopy;
    private String CreationTime;
    private String DepartmentName;
    private String RegionId;
    private String AutoSnapshotPolicyName;
    private TagsEty Tags;
    private int ResourceGroup;
    private String ResourceGroupName;

    public AutoSnapshotPolicyEty() {
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTimePoints() {
        return TimePoints;
    }

    public void setTimePoints(String timePoints) {
        TimePoints = timePoints;
    }

    public int getShared() {
        return shared;
    }

    public void setShared(int shared) {
        this.shared = shared;
    }

    public int getVolumeNums() {
        return VolumeNums;
    }

    public void setVolumeNums(int volumeNums) {
        VolumeNums = volumeNums;
    }

    public String getAutoSnapshotPolicyId() {
        return AutoSnapshotPolicyId;
    }

    public void setAutoSnapshotPolicyId(String autoSnapshotPolicyId) {
        AutoSnapshotPolicyId = autoSnapshotPolicyId;
    }

    public int getRetentionDays() {
        return RetentionDays;
    }

    public void setRetentionDays(int retentionDays) {
        RetentionDays = retentionDays;
    }

    public String getRepeatWeekdays() {
        return RepeatWeekdays;
    }

    public void setRepeatWeekdays(String repeatWeekdays) {
        RepeatWeekdays = repeatWeekdays;
    }

    public int getDiskNums() {
        return DiskNums;
    }

    public void setDiskNums(int diskNums) {
        DiskNums = diskNums;
    }

    public int getCopiedSnapshotsRetentionDays() {
        return CopiedSnapshotsRetentionDays;
    }

    public void setCopiedSnapshotsRetentionDays(int copiedSnapshotsRetentionDays) {
        CopiedSnapshotsRetentionDays = copiedSnapshotsRetentionDays;
    }

    public int getDepartment() {
        return Department;
    }

    public void setDepartment(int department) {
        Department = department;
    }

    public boolean isEnableCrossRegionCopy() {
        return EnableCrossRegionCopy;
    }

    public void setEnableCrossRegionCopy(boolean enableCrossRegionCopy) {
        EnableCrossRegionCopy = enableCrossRegionCopy;
    }

    public String getCreationTime() {
        return CreationTime;
    }

    public void setCreationTime(String creationTime) {
        CreationTime = creationTime;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getRegionId() {
        return RegionId;
    }

    public void setRegionId(String regionId) {
        RegionId = regionId;
    }

    public String getAutoSnapshotPolicyName() {
        return AutoSnapshotPolicyName;
    }

    public void setAutoSnapshotPolicyName(String autoSnapshotPolicyName) {
        AutoSnapshotPolicyName = autoSnapshotPolicyName;
    }

    public TagsEty getTags() {
        return Tags;
    }

    public void setTags(TagsEty tags) {
        Tags = tags;
    }

    public int getResourceGroup() {
        return ResourceGroup;
    }

    public void setResourceGroup(int resourceGroup) {
        ResourceGroup = resourceGroup;
    }

    public String getResourceGroupName() {
        return ResourceGroupName;
    }

    public void setResourceGroupName(String resourceGroupName) {
        ResourceGroupName = resourceGroupName;
    }
}
