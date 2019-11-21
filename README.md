<!-- HEADER -->
<h1 align="center">
<!--
  <br>
    <a href=""><img src="src/main/resources/logger/logger-icon.png" alt="Logisim Icon"></a>
-->  
  <br>
    Logger
  <br>
</h1>

<h4 align="center">A super simple logger that formats and logs everything in System.out/err to both the console as well as a 
file.</h4>


<!-- SHIELDS -->
<p align="center">
    <!-- Issues -->    
    <a href="https://img.shields.io/github/issues/dadler64/Logger">
        <img src="https://img.shields.io/github/issues/dadler64/Logger.svg"
            alt="Logger Issues">
    </a>
    <!-- License -->
    <a href="https://img.shields.io/github/license/dadler64/Logger">
        <img src="https://img.shields.io/github/license/dadler64/Logger.svg"
            alt="Logger License">
    </a>
    <!-- Release Version -->
      <a href="https://img.shields.io/github/release/dadler64/Logger">
        <img src="https://img.shields.io/github/release/dadler64/Logger.svg"
              alt="Release Version">
      </a>
</p>

<!-- LINKS -->
<p align="center">
  <a href="#about">About</a> •
  <a href="#features">Features</a> •
  <a href="#how-to-use">How To Use</a> •
  <a href="#contributing">Contributing</a>
</p>

## About
I wanted to log my programs but I did not like any of the ones out there and was too lazy to find one that worked well and was 
easy to set up so I just made this simple logger to fill my needs for the time being. I plan on adding the ability to set a log 
level later.

Log file names are formatted as `<yyyy-MM-dd>_<HHmmss>_<filename>.log`.

## Features
  - [x] Uses System.out/err to simplify logging.
  - [x] SplitOutputSream which sends the output stream to both System.out/err as well as a log file.
  - [x] Formatted log files for easier viewing.
  - [ ] Ability to set custom logging level.
  - [ ] `debugln()` shows class name and file number.

## How To Use
  * Start logging to a file: `Logger.start();`
  * Start logging to a file name you want: `Logger.start("filename");`
  * Stop logging to a file: `Logger.close();` 
  * Output information: `Logger.infoln("example output");` 
  * Output debugger info (WIP): `Logger.debuglnln("example output");` 
  * Output warning: `Logger.warnln("example output");` 
  * Output error that does not quit: `Logger.errorln("example error");` 
  * Output error that quits with a status of '-1': `Logger.errorln("example error", true);` 
  * Output error that quits with a status of '-6': `Logger.errorln("example error", true, -6);` 
  * Output error that handles an exception: `Logger.errorln(exception , false);` 

## Contributing
  For now if you would like to contribute check out any open [issues](https://github.com/dadler64/Logger/issues) which could be resolved.
