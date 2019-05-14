package com.group.restcontrollers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.bean.ErrorBean;
import com.group.bean.FourSquareConnConfig;
import com.group.bean.VenueDetailsBean;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

/**
 * <h1>LocatePlaces</h1> The LocatePlaces program provides methods to use
 * foursquare API to locate places near you and provide more details.
 * 
 *
 * @author Sandeep nair
 * @version 1.0
 * @since 2019-05-12
 */
@RestController
public class LocatePlaces {

	@Autowired
	FourSquareConnConfig fourSquareConnConfig;

	@Autowired
	ErrorBean errorBean;

	/**
	 * This method is used to search a location and entire result is returned back
	 * to client. This ensure client can decide on which parameter to be used from
	 * output.It also contains list of all the details of the place searched.
	 * 
	 * @param ll This denotes longitude and latitude. Eg:40.7337621,-74.0095604
	 * @return String This returns JSON output to client.
	 */

	@GetMapping("/searchlocation")
	ResponseEntity<Map> searchLocation(@RequestParam(value = "ll") String ll) throws FoursquareApiException {

		FoursquareApi foursquareApi = new FoursquareApi(fourSquareConnConfig.getClientId(),
				fourSquareConnConfig.getClientSecret(), "https://api.foursquare.com/v2/");
		Result<VenuesSearchResult> result = foursquareApi.venuesSearch(ll, null, null, null, null, null, null, null,
				null, null, null, null, null);
		return createVenueMap(result);


	}

	/**
	 * This method is used to search a location and filter by category/type.
	 * 
	 * @param name This denotes location name.
	 * @param ll   This denotes longitude and latitude. Eg:40.7337621,-74.0095604
	 * @return Map Incase of STATUS CODE=200 , map would contain VenueDetailsBean
	 *         object.Incase of error, it would contain ErrorBean object. Client
	 *         need to check MAP
	 * @throws Exception 
	 * 
	 */

	@GetMapping("/filterbycategory")
	ResponseEntity<Map> filterByCategory(@RequestParam(value = "name", defaultValue ="NA") String name,
			@RequestParam(value = "ll", defaultValue = "0") String ll,
			@RequestParam(value = "category") String category) throws Exception {

		FoursquareApi foursquareApi = new FoursquareApi(fourSquareConnConfig.getClientId(),
				fourSquareConnConfig.getClientSecret(), "https://api.foursquare.com/v2/");
		Result<VenuesSearchResult> result = null;
		Map venueMap = new LinkedHashMap();
		if (!ll.equalsIgnoreCase("0")) {
			result = foursquareApi.venuesSearch(ll, null, null, null, category.trim(), null, null, null, null, null,
					null, null, null);
		} else if(!name.equalsIgnoreCase("NA")){
			result = foursquareApi.venuesSearch(name.trim(), category.trim(), null, null, null, null, null, null);
		}
		if (result != null)
		    {
			   return createVenueMap(result);
		    }
		else {
			throw new Exception("neither ll nor name attribute available under request.");
		}

	}

	/**
	 * This method is used to populate venue and respond back along with status code.
	 * 
	 * @param result This denotes overall  venue object.
	 * @return ResponseEntity Incase of STATUS CODE=200 , it would contain VenueMap
	 *         object.Incase of error, it would contain ErrorBean object. Client
	 *         need to check based on response code.
	 * 
	 */
	ResponseEntity createVenueMap(Result<VenuesSearchResult> result) {
		Map venueMap = new LinkedHashMap();
		if (result.getMeta().getCode() == 200)// if response is successful
		{

			for (CompactVenue venue : result.getResult().getVenues()) {
				VenueDetailsBean venueDetailsBean = new VenueDetailsBean();
				venueDetailsBean.setVenueId(venue.getId());
				venueDetailsBean.setVenueName(venue.getName());
				if (venue.getCategories().length > 0) {
					venueDetailsBean.setCategoryId(venue.getCategories()[0].getId());
					venueDetailsBean.setCategoryName(venue.getCategories()[0].getPluralName());
				}
				venueMap.put(venue.getId(), venueDetailsBean);

			}
		}

		else {
			errorBean.setCode(result.getMeta().getCode());
			errorBean.setErrorType(result.getMeta().getErrorType());
			errorBean.setErrorDetail(result.getMeta().getErrorDetail());
			venueMap.put("ERRORDETAIL", errorBean);
		}
		ResponseEntity respEntity=null;
		if (venueMap.containsKey("ERRORDETAIL"))
		{
			respEntity =  new ResponseEntity<Map>(venueMap, HttpStatus.BAD_REQUEST);
		}
		else
		{
			respEntity = new ResponseEntity<Map>(venueMap, HttpStatus.OK);
		}
		return respEntity;
		
	}
}
