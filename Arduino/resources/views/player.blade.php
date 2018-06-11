@extends('layouts.app')

@section('content')
<script src="{{ asset('js/music.js') }}" defer></script>
 <meta name="csrf-token" content="{{ csrf_token() }}" />
<link href="{{ asset('css/style.css') }}" rel="stylesheet">
    <meta name="csrf-token" content="{{ csrf_token() }}" />
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Musicplayer</div>
                <div class="row" style="margin-left:0px;margin-right:0px;">
                      <div id="leftcolumn" class="col-sm-4">                       
                        <div class="list-group" id="list-tab" role="tablist">
                        {!! Form::open(array('action' => 'DataController@postStation', 'method' => 'POST', 'id' => '{{$post}}')) !!}
                          @csrf
                        @foreach($response as $post)
                          <input name="radio" type="submit" id="{{$post}}" value="{{$post}}" class="list-group-item list-group-item-action" role="tab"></input>
                          @endforeach
                        {!! Form::close() !!}
                        </div>
                      </div>
                    <div id="centercolumn" class="col-sm-4">
                        <div class="tab-content" id="nav-tabContent">
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



<script type="text/javascript">


    $.ajaxSetup({

        headers: {

            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')

        }

    });


    $(".list-group-item.list-group-item-action").click(function(e){

        e.preventDefault();


        var radio = $("input[name=radio]").val();
        var radio = $("input[name=volume]").val();

        $.ajax({

           type:'POST',

           url:'/player',

           data:{radio:radio, volume:volume},

           success:function(data){

              alert(data.success);

           }

        });


    });

</script>
@endsection
