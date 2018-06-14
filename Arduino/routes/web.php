<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Auth::routes();

Route::get('/player', 'HomeController@index')->name('player');

Route::post('start',  array('uses' => 'DataController@postStart'));

Route::get('/player', 'DataController@getStationnames');

Route::delete('/start', 'DataController@postStart');

Route::post('stop', array('uses' => 'DataController@postStop'));

Route::post('station',  array('uses' => 'DataController@postStation'));

Route::delete('/stop','DataController@postStop');

Route::post('volume', array('uses' => 'DataController@postVolume'));

Route::delete('/volume','DataController@postVolume');


// Route::get('/player', 'DataController@postVolume');

