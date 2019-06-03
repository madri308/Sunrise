
package sunrise;


import com.teamdev.jxmaps.*;
import com.teamdev.jxmaps.swing.MapView;

import javax.swing.*;
import java.awt.*;

public class Sunrise extends MapView {
    public Sunrise(MapViewOptions options) {
        super(options);
        setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                if (status == MapStatus.MAP_STATUS_OK) {
                    final Map map = getMap();
                    map.setZoom(5.0);
                    GeocoderRequest request = new GeocoderRequest(map);
                    request.setAddress("Costa Rica");   // Se escoje el lugar
                       

                    getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
                        @Override
                        public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
                            if (status == GeocoderStatus.OK) {
                                map.setCenter(result[0].getGeometry().getLocation());
                                System.out.println(result[0].getGeometry().getLocation());
                                Marker marker = new Marker(map);
                                marker.setPosition(result[0].getGeometry().getLocation());   // Se pone el marcador en el centro
     
                                System.out.println(result[0].getFormattedAddress());
                                
                                map.addEventListener("click", new MapMouseEvent() {
                                    @Override
                                    public void onEvent(MouseEvent mouseEvent) {
                                        //marker.remove();
                                        // Closing initially created info window
                                        //infoWindow.close();
                                        // Creating a new marker
                                        //Marker marker = new Marker(map);
                                        // Move marker to the position where user clicked
                                        marker.setPosition(mouseEvent.latLng());                 // Coloca el marcador en la posicion del click
                                        
                                      

                                        // Adding event listener that intercepts clicking on marker
                                        marker.addEventListener("click", new MapMouseEvent() {
                                            @Override
                                            public void onEvent(MouseEvent mouseEvent) {
                                                // Removing marker from the map
                                                
                                            }
                                        });
                                    }
                                });
                                /*
                                final InfoWindow window = new InfoWindow(map);
                                window.setContent("Como costo hacer esto...!");
                                window.open(map, marker);*/
                            }
                        }
                    });
                }
            }
        });
    }

    public static void main(String[] args) {

        MapViewOptions options = new MapViewOptions();
        options.importPlaces();
        options.setApiKey("AIzaSyBaM_jlKJGwu3F9qfPxJjcFR3AmxoECFAA");
        final Sunrise mapView = new Sunrise(options);
/*
        //JFrame a = new JFrame
        venPrueba frame = new venPrueba("Mapa");
        

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(mapView, BorderLayout.CENTER);
        
        
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        
      
        
        
        
       */
        JFrame frame = new JFrame("Mapa");
        

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(mapView, BorderLayout.CENTER);
        
        
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      
       
    }
}
