package project.v1nz.covidtracker.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import project.v1nz.covidtracker.models.LocationStat;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static java.net.URI.create;

@Service
public class RecoveredDataService {

     private static String RECOVERED_DATA_URL ="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";

    private List<LocationStat> allRecoveredStats = new ArrayList<>();

    public List<LocationStat> getAllRecoveredStats() {
        return allRecoveredStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchRecoveredData() throws IOException, InterruptedException {
        List<LocationStat> newRecoveredStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(create(RECOVERED_DATA_URL)).build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(httpResponse.body());
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            LocationStat locationStat = new LocationStat();
            locationStat.setState(record.get("Province/State"));
            locationStat.setCountry(record.get("Country/Region"));
            int latestCases =Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases=Integer.parseInt(record.get(record.size() - 2));
            locationStat.setLatestTotalRecoveredCases(latestCases);
            locationStat.setRecoveredDiffFromPrevDay(latestCases-prevDayCases);
            newRecoveredStats.add(locationStat);
        }
        this.allRecoveredStats = newRecoveredStats;
    }
}
