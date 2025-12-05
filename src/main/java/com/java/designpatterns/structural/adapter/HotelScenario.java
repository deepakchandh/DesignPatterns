package com.java.designpatterns.structural.adapter;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/*
THE DATA may be from different source in different styles like JSON, XML(soap), graphql.

with my HotelSupplierClient I try to have data in the format of List<HotelPrice>.
 */



interface HotelSupplierClient {
    List<HotelPrice> fetchPrices(HotelSearchRequest request);
}
class HotelSearchRequest {
    private String city;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int guests;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }
}
class HotelPrice {
    private String hotelName;
    private double pricePerNight;
    private String supplier;

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}

/*
class AgodaAdapter implements HotelSupplierClient {

    private final AgodaApiClient agodaApi; // hypothetical client

    public AgodaAdapter(AgodaApiClient agodaApi) {
        this.agodaApi = agodaApi;
    }

    @Override
    public List<HotelPrice> fetchPrices(HotelSearchRequest request) {
        AgodaRequest agodaReq = new AgodaRequest(
                request.getCity(), request.getCheckIn(), request.getCheckOut(), request.getGuests()
        );

        AgodaResponse agodaResp = agodaApi.getHotelPrices(agodaReq);

        return agodaResp.getResults().stream()
                .map(r -> new HotelPrice(r.getHotelName(), r.getRate(), "Agoda"))
                .collect(Collectors.toList());
    }
}


class OyoAdapter implements HotelSupplierClient {
    private final OyoApi oyoApi;

    public OyoAdapter(OyoApi oyoApi) {
        this.oyoApi = oyoApi;
    }

    @Override
    public List<HotelPrice> fetchPrices(HotelSearchRequest request) {
        OyoResponse resp = oyoApi.query(request);
        return resp.getData().stream()
                .map(d -> new HotelPrice(d.getHotelName(), d.getNightlyRate(), "OYO"))
                .collect(Collectors.toList());
    }
}
*/


class HotelAggregatorService {
    private final List<HotelSupplierClient> suppliers;

    public HotelAggregatorService(List<HotelSupplierClient> suppliers) {
        this.suppliers = suppliers;
    }

    public List<HotelPrice> getAllPrices(HotelSearchRequest request) {
        return suppliers.stream()
                .flatMap(s -> s.fetchPrices(request).stream())
                .collect(Collectors.toList());
    }
}

public class HotelScenario {

    /*
    public static void main(String[] args) {
        HotelSupplierClient agoda = new AgodaAdapter(new AgodaApiClient());
        HotelSupplierClient mmt = new MakeMyTripAdapter(new MakeMyTripClient());
        HotelSupplierClient oyo = new OyoAdapter(new OyoApi());

        HotelAggregatorService aggregator = new HotelAggregatorService(List.of(agoda, mmt, oyo));

        HotelSearchRequest req = new HotelSearchRequest("Bangalore", LocalDate.now(), LocalDate.now().plusDays(2), 2);
        List<HotelPrice> prices = aggregator.getAllPrices(req);

        prices.forEach(p -> System.out.println(p.getSupplier() + ": " + p.getHotelName() + " - " + p.getPricePerNight()));
    }

     */
}

