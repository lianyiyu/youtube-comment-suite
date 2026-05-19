package io.mattw.youtube.commentsuite.db;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime; 

public class Group {

    private final String groupId;
    private String name;
    private LocalDateTime lastRefreshed; 

    /**
     * Used when creating a new group.
     */
    public Group(String name) {
        this.name = name;
        this.groupId = generateId();
        this.lastRefreshed = null;
    }

    /**
     * Use for database init.
     */
    public Group(String groupId, String name, LocalDateTime lastRefreshed) {
        this.groupId = groupId;
        this.name = name;
        this.lastRefreshed = lastRefreshed;
    }

    public Group(String groupId, String name) {
        this(groupId, name, null);
    }

    public String getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLastRefreshed() {
        return lastRefreshed;
    }

    public void setLastRefreshed(LocalDateTime lastRefreshed) {
        this.lastRefreshed = lastRefreshed;
    }

    public String toString() {
        return name;
    }

    public int hashCode() {
        return groupId.hashCode();
    }

    public boolean equals(Object o) {
        return o instanceof Group && o.hashCode() == hashCode();
    }

    private String generateId() {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(StandardCharsets.UTF_8.encode(System.nanoTime() + this.name));
            return String.format("%032x", new BigInteger(1, md5.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            return String.valueOf(System.nanoTime());
        }
    }
}
