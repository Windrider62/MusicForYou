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

Route::get('/player', 'DataController@getStationnames');

Route::post('/player', 'DataController@postStart');

Route::delete('/player', 'DataController@postStart');

Route::post('/stop', 'DataController@postStop');

Route::delete('/stop','DataController@postStop');

Route::post('/volume', 'DataController@postVolume');

Route::delete('/volume','DataController@postVolume');

Route::post('/station', 'DataController@postStation');

Route::delete('/station','DataController@postStation');
// Route::get('/player', 'DataController@postVolume');

