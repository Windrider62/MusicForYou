@extends('layouts.app')

@section('content')
<script src="{{ asset('js/music.js') }}" defer></script>
<link href="{{ asset('css/style.css') }}" rel="stylesheet">
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Musicplayer</div>
                <div class="row" style="margin-left:0px;margin-right:0px;">
                      <div id="leftcolumn" class="col-sm-4">
                        <div class="list-group" id="list-tab" role="tablist">
                          <a class="list-group-item list-group-item-action active" id="list-song1-list" data-toggle="list" href="#list-song1" role="tab" aria-controls="song1">song1</a>
                          <a class="list-group-item list-group-item-action" id="list-song2-list" data-toggle="list" href="#list-song2" role="tab" aria-controls="song2">song2</a>
                          <a class="list-group-item list-group-item-action" id="list-song3-list" data-toggle="list" href="#list-song3" role="tab" aria-controls="messages">song3</a>
                          <a class="list-group-item list-group-item-action" id="list-song4-list" data-toggle="list" href="#list-song4" role="tab" aria-controls="settings">song4</a>
                        </div>
                      </div>
                    <div id="centercolumn" class="col-sm-4">
                        <div class="tab-content" id="nav-tabContent">
                            <img id="art" align="center" src="{{ asset('images/art.jpg') }}" height="250" width="250"><br>
                            <div class="tab-pane fade show active" id="list-song1" role="tabpanel" aria-labelledby="list-song1-list"><h4 id="song" align="center">Artist<span id="list-song1">song1</span></h4><br></div>
                            <div class="tab-pane fade" id="list-song2" role="tabpanel" aria-labelledby="list-song2-list"><h4 id="song" align="center">Artist<span id="list-song2">song2</span></h4><br></div>
                            <div class="tab-pane fade" id="list-song3" role="tabpanel" aria-labelledby="list-song3-list"><h4 id="song" align="center">Artist<span id="list-song3">song3</span></h4><br></div>
                            <div class="tab-pane fade" id="list-song4" role="tabpanel" aria-labelledby="list-song4-list"><h4 id="song" align="center">Artist<span id="list-song4">song4</span></h4><br></div>
                            <div align="center">
                            <input type="image" src="{{ asset('images/previous.png') }}" height="75" width="75">
                            <input type="image" src="{{ asset('images/play.png') }}" height="75" width="75">
                            <input type="image" src="{{ asset('images/next.png') }}" height="75" width="75">
                            </div>
                        </div>
                    </div>
                    <div id="rightcolumn" class="col-sm-4">
                        <ul class="list-group">
                            <li class="list-group-item">Player 1  <input id="vol-control1" type="range" min="0" max="100" step="1" oninput="SetVolume(this.value)" onchange="SetVolume(this.value)"></input></li>
                            <li class="list-group-item">Player 2  <input id="vol-control2" type="range" min="0" max="100" step="1" oninput="SetVolume(this.value)" onchange="SetVolume(this.value)"></input></li>
                            <li class="list-group-item">Player 3  <input id="vol-control3" type="range" min="0" max="100" step="1" oninput="SetVolume(this.value)" onchange="SetVolume(this.value)"></input></li>
                            <li class="list-group-item">Player 4  <input id="vol-control4" type="range" min="0" max="100" step="1" oninput="SetVolume(this.value)" onchange="SetVolume(this.value)"></input></li>
                            <li class="list-group-item">Player 5  <input id="vol-control5" type="range" min="0" max="100" step="1" oninput="SetVolume(this.value)" onchange="SetVolume(this.value)"></input></li>
                        </ul>
                    </div>
                </div>
            </div>
                <div class="card-body">
                    @if (session('status'))
                        <div class="alert alert-success">
                            {{ session('status') }}
                        </div>
                    @endif
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
