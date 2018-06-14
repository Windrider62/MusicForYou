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
                        {{ Form::open(array('action' => 'DataController@postStation')) }}
                            {{ csrf_field() }}
                        @foreach($response as $post)
                          <input name="radio" type="submit" id="{{$post}}" value="{{$post}}" class="list-group-item list-group-item-action" role="tab"></input>
                          @endforeach
                        {{ Form::close() }}
                        </div>
                      </div>
                    <div id="centercolumn" class="col-sm-4">
                        <div class="tab-content" id="nav-tabContent">
                            <div align="center">
                           {{ Form::open(array('action' => 'DataController@postStop')) }}
                                {{ csrf_field() }}
                            <input type="image" src="{{ asset('images/pause.png') }}" height="75" width="75">
                           {{ Form::close() }}

                           {{ Form::open(array('action' => 'DataController@postStart')) }}
                                {{ csrf_field() }}
                            <input type="image" src="{{ asset('images/play.png') }}" height="75" width="75">
                           {{ Form::close() }}
                            </div>
                        </div>
                    </div>
                    <div id="rightcolumn" class="col-sm-4">
                        {!! Form::open(array('action' => 'DataController@postVolume', 'method' => 'post')) !!}
                            {{ csrf_field() }}
                        <ul class="list-group">
                            <li class="list-group-item">{!! Form::range('volume', null, ['min'=>0,'max'=>150]) !!} </li>
                        <input class="btn-click" type="submit"></input>
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

        $.ajax({

           type:'POST',

           url:'station',

           data:{radio:radio},

           success:function(data){

              alert(data.success);

           }

        });


    });

        $(".btn-click").click(function(e){

        e.preventDefault();


        var radio = $("input[name=volume]").val();

        $.ajax({

           type:'POST',

           url:'station',

           data:{volume:volume},

           success:function(data){

              alert(data.success);

           }

        });


    });

</script>
@endsection
