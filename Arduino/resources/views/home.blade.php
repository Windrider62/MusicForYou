@extends('layouts.app')

@section('content')
<script src="{{ asset('js/music.js') }}" defer></script>
<link href="{{ asset('css/style.css') }}" rel="stylesheet">
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Musicplayer</div>
        <main>
            <h1>Simple Clean HTML5 Audio Player</h1>
            <audio id="a1" src="sample.mp3" crossorigin="anonymous"></audio>
        </main>
                   

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
