<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class GuzzlePostController extends Controller
{
    public function store(Request $request)
    {
        $data = new GuzzlePost();
        $data->name=$request->get('name');
        $data->save();
        return response()->json('Successfully added');

    }

    public function index()
    {
        $data = GuzzlePost::all();
        return response()->json($data);
    }
}
