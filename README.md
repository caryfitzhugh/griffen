# griffin
Clojurescript / Reagent based alternative to OSD


## Components
* Clojurescript
* Reagent

Using SVG internally

## Requirements
*  Infinitely zoomable with scroll wheel
*  Movable with drag
*  Able to manage arbitrary HTML elements with anchors / x,y
*  Events when things move / change
*  Ellipse / Circle / Line / Marker
*  Measure (we need to get the actual image sizes, and know the dimensions of things inside the viewer)
  * http://crashcourse.housegordon.org/D3JS-Absolute-Units.html 
*  Capture Image
*  Full-Screen
*  Emits state changes on every change (complete state and diff)
*  Annotations on / off
*  Overview (minimap)
*  Compass / Movement thing (rotate the canvas about a central point)
*  Usable on a tablet (touch, etc)
*  Keyboard Shortcuts
  *  Esc - Sets the compass to origin
*  Match current images / tiles API
*  

## Design Goals
* Simple initialization (function call with an element to fill.
  * Also the image path to read from / image meta_data
* functional - so you can get the entire VST state and ship it out to another spot
* Emit changed state with a hash of the current state, + diff. If you need a new full state, we can give it!
* Simpler testing facility (test things ourselves and provide a clean / simple API)
* Provide the buttons - but leave it up to CSS to provide the styling.

