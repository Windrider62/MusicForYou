<?php

namespace App\Http\Controllers;

use GuzzleHttp\Client;
use GuzzleHttp\RequestOptions;
use Illuminate\Http\Request;
use App\Http\Requests;

class DataController extends Controller
{

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

		$response = $client->post('http://localhost:8080/node/changevolume/30',
		    ['body' => json_encode(
		        [
		            'http://172.20.10.4', 'http://172.20.10.4',

		        ]
		    )]
		);

		echo '<pre>' . var_export($response->getStatusCode(), true) . '</pre>';
		echo '<pre>' . var_export($response->getBody()->getContents(), true) . '</pre>';

		return response()->json(array('response'=> $response), 200);

	}

	public function postStation()

	{

		$client = new \GuzzleHttp\Client([
		    'headers' => [ 'Content-Type' => 'application/json' ]
		]);

		$response = $client->post('http://localhost:8080/node/changeradiostation/radio 1',
		    ['body' => json_encode(
		        [
		            'http://192.168.43.183', 'http://192.168.43.183',

		        ]
		    )]
		);

		echo '<pre>' . var_export($response->getStatusCode(), true) . '</pre>';
		echo '<pre>' . var_export($response->getBody()->getContents(), true) . '</pre>';

		return response()->json(array('response'=> $response), 200);

	}

    public function postStart()

	{

		$client = new \GuzzleHttp\Client([
		    'headers' => [ 'Content-Type' => 'application/json' ]
		]);

		$response = $client->post('http://localhost:8080/node/start',
		    ['body' => json_encode(
		        [
		            'http://192.168.43.183', 'http://192.168.43.183'
		        ]
		    )]
		);

		echo '<pre>' . var_export($response->getStatusCode(), true) . '</pre>';
		echo '<pre>' . var_export($response->getBody()->getContents(), true) . '</pre>';

		return response()->json(array('response'=> $response), 200);

	}

	public function postStop()

	{

		$client = new \GuzzleHttp\Client([
		    'headers' => [ 'Content-Type' => 'application/json' ]
		]);

		$response = $client->post('http://localhost:8080/node/stop',
		    ['body' => json_encode(
		        [
		            'http://172.20.10.4', 'http://172.20.10.4'
		        ]
		    )]
		);

		echo '<pre>' . var_export($response->getStatusCode(), true) . '</pre>';
		echo '<pre>' . var_export($response->getBody()->getContents(), true) . '</pre>';

		return response()->json(array('response'=> $response), 200);

	}

}
