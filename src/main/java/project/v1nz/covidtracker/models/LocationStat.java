package project.v1nz.covidtracker.models;

public class LocationStat {

    private String state;
    private String country;
    private int latestTotalInfectedCases;
    private int infectedDiffFromPrevDay;

    private int latestTotalDeathCases;
    private int deathDiffFromPrevDay;

    private int latestTotalRecoveredCases;
    private int recoveredDiffFromPrevDay;

    public int getLatestTotalDeathCases() {
        return latestTotalDeathCases;
    }

    public void setLatestTotalDeathCases(int latestTotalDeathCases) {
        this.latestTotalDeathCases = latestTotalDeathCases;
    }

    public int getDeathDiffFromPrevDay() {
        return deathDiffFromPrevDay;
    }

    public void setDeathDiffFromPrevDay(int deathDiffFromPrevDay) {
        this.deathDiffFromPrevDay = deathDiffFromPrevDay;
    }

    public int getLatestTotalRecoveredCases() {
        return latestTotalRecoveredCases;
    }

    public void setLatestTotalRecoveredCases(int latestTotalRecoveredCases) {
        this.latestTotalRecoveredCases = latestTotalRecoveredCases;
    }

    public int getRecoveredDiffFromPrevDay() {
        return recoveredDiffFromPrevDay;
    }

    public void setRecoveredDiffFromPrevDay(int recoveredDiffFromPrevDay) {
        this.recoveredDiffFromPrevDay = recoveredDiffFromPrevDay;
    }

    public int getInfectedDiffFromPrevDay() {
        return infectedDiffFromPrevDay;
    }

    public void setInfectedDiffFromPrevDay(int infectedDiffFromPrevDay) {
        this.infectedDiffFromPrevDay = infectedDiffFromPrevDay;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getLatestTotalInfectedCases() {
        return latestTotalInfectedCases;
    }

    public void setLatestTotalInfectedCases(int latestTotalInfectedCases) {
        this.latestTotalInfectedCases = latestTotalInfectedCases;
    }

    @Override
    public String toString() {
        return "LocationStat{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalInfectedCases=" + latestTotalInfectedCases +
                ", infectedDiffFromPrevDay=" + infectedDiffFromPrevDay +
                ", latestTotalDeathCases=" + latestTotalDeathCases +
                ", deathDiffFromPrevDay=" + deathDiffFromPrevDay +
                ", latestTotalRecoveredCases=" + latestTotalRecoveredCases +
                ", recoveredDiffFromPrevDay=" + recoveredDiffFromPrevDay +
                '}';
    }
}
