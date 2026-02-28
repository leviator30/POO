package org.example;

/**
 * Este folosit pattern-ul Builder in crearea obiectelor de tipul Museum.
 */
public class Museum {
    private String name;
    private long code;
    private long supervisorCode;
    private Location location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public long getSupervisorCode() {
        return supervisorCode;
    }

    public void setSupervisorCode(long supervisorCode) {
        this.supervisorCode = supervisorCode;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Museum(MuseumBuilder builder) {
        this.name = builder.name;
        this.code = builder.code;
        this.supervisorCode = builder.supervisorCode;
        this.location = builder.location;
    }

    public static class MuseumBuilder {
        private String name;
        private long code;
        private long supervisorCode;
        private Location location;
        public MuseumBuilder setName(String name) {
            this.name = name;
            return this;
        }
        public MuseumBuilder setCode(long code) {
            this.code = code;
            return this;
        }
        public MuseumBuilder setSupervisorCode(long supervisorCode) {
            this.supervisorCode = supervisorCode;
            return this;
        }
        public MuseumBuilder setLocation(Location location) {
            this.location = location;
            return this;
        }
        public Museum build() {
            return new Museum(this);
        }
    }
}
