<?php

namespace App\Http\Controllers;

use GuzzleHttp\Client;
use GuzzleHttp\RequestOptions;
use Illuminate\Http\Request;
use App\Http\Requests;

class DataController extends Controller
{

    public function player($player)

    {
        $client = new \GuzzleHttp\Client();

	    $request = $client->get('http://localhost:8080/stationnames');

		$response = json_decode($request->getBody()->getContents());

        return view('player');

    }

	public function getStationnames()

	{

	    $client = new \GuzzleHttp\Client();

	    $request = $client->get('http://localhost:8080/stationnames');

		$response = json_decode($request->getBody()->getContents());

		return view('player', compact('response'));

	}

	public function postVolume()

	{


		$client = new \GuzzleHttp\Client([
		    'headers' => [ 'Content-Type' => 'application/json' ]
		]);

		$volume = request()->get('volume');

		$response = $client->post('http://localhost:8080/node/changevolume/'.$volume,
		    ['body' => json_encode(
		        [
		            'http://172.20.10.7', 'http://172.20.10.10',

		        ]
		    )]
		);

	    // $client = new \GuzzleHttp\Client();

	    $request = $client->get('http://localhost:8080/stationnames');

		$response = json_decode($request->getBody()->getContents());

		$debug= 'volume';

		return view('player', compact('response'));

	}

	public function postStation(Request $request)

	{

		$client = new \GuzzleHttp\Client([
		    'headers' => [ 'Content-Type' => 'application/json' ]
		]);
          
		$input = request()->get('radio');

		$url = 'http://localhost:8080/node/changeradiostation/'.$input;

		$response = $client->post('http://localhost:8080/node/changeradiostation/'.$input,
		    ['body' => json_encode(
		        [
		            'http://172.20.10.7', 'http://172.20.10.10',

		        ]
		    )]
		);

	    $request = $client->get('http://localhost:8080/stationnames');

		$response = json_decode($request->getBody()->getContents());

		$debug= 'station';

		return view('player', compact('response'));

	}

	public function postStop()

	{

		$client = new \GuzzleHttp\Client([
		    'headers' => [ 'Content-Type' => 'application/json' ]
		]);

		$response = $client->post('http://localhost:8080/node/stop', 		    
		['body' => json_encode(
		        [
		            'http://172.20.10.7', 'http://172.20.10.10',

		        ]
		    )]
		);

	    $request = $client->get('http://localhost:8080/stationnames');

		$response = json_decode($request->getBody()->getContents());

		$debug= 'stop';

		return view('player', compact('response'));

	}

	public function postStart()

	{

		$client = new \GuzzleHttp\Client([
		    'headers' => [ 'Content-Type' => 'application/json' ]
		]);

		$response = $client->post('http://localhost:8080/node/start', 		    
		['body' => json_encode(
		        [
		            'http://172.20.10.7', 'http://172.20.10.10',

		        ]
		    )]
		);

	    $request = $client->get('http://localhost:8080/stationnames');

		$response = json_decode($request->getBody()->getContents());

		$debug= 'start';

		return view('player', compact('/player','response'));

	}

}
