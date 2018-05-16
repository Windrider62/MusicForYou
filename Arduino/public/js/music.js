window.SetVolume = function(val)
{
    var player = document.getElementById('player');
    console.log('Before: ' + player.volume);
    player.volume = val / 100;
    console.log('After: ' + player.volume);
}