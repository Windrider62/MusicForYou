@extends('layouts.app')

@section('content')
<script src="{{ asset('js/music.js') }}" defer></script>
 <meta name="csrf-token" content="{{ csrf_token() }}" />
<link href="{{ asset('css/style.css') }}" rel="stylesheet">
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Musicplayer</div>
                <div class="row" style="margin-left:0px;margin-right:0px;">
                      <div id="leftcolumn" class="col-sm-4">                       
                        <div class="list-group" id="list-tab" role="tablist">
                        @foreach($response as $post)
                        {!! Form::open(array('action' => 'DataController@postStation', 'method' => 'post')) !!}
                          <a class="list-group-item list-group-item-action" onclick="window.location='{{action('DataController@postStation')}}'" data-toggle="list" href="{{$post}}" role="tab">{{$post}}</a>
                        {!! Form::close() !!}
                        @endforeach
                        </div>
                      </div>
                    <div id="centercolumn" class="col-sm-4">
                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane active" id="{{$post}}" role="tabpanel">
                                <img id="art" align="center" src="{{ asset('images/art.jpg') }}" height="250" width="250"><br>
                            </div>
                            <div align="center">
                            <form method="post">
                            @csrf
                            <input type="image" onclick="window.location='{{action('DataController@postStart')}}'" src="{{ asset('images/play.png') }}" height="75" width="75">
                            <input type="image" onclick="window.location='{{action('DataController@postStop')}}'" src="{{ asset('images/pause.png') }}" height="75" width="75">
                                <input name="_token" type="hidden" id="_token" value="{{ csrf_token() }}" />
                            </form>
                            </div>
                        </div>
                    </div>
                    <div id="rightcolumn" class="col-sm-4">
                        {!! Form::open(array('action' => 'DataController@postVolume', 'method' => 'post')) !!}
                        <ul class="list-group">
                            <li class="list-group-item">{!! Form::range('volume', 'fewfwe') !!} </li>
                            {{ Form::submit('Verstuur') }}
                        </ul> 
                        {!! Form::close() !!}
                    </form>
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
<script>
    $('#myList a').on('click', function (e) {
  e.preventDefault()
  $(this).tab('show')
})

    $(document).ready(function () {

    $.ajaxSetup({
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        }
    });

$.ajax({
    url: '/station',
    type: 'POST',
    dataType: 'JSON',
    data: $('form').serialize()
});

});

</script>
@endsection
