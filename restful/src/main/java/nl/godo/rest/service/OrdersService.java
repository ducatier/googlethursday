package nl.godo.rest.service;


import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * http://localhost:8080/rest-example/orders/1/bob
 * http://localhost:8080/rest-example/orders/1
 * http://localhost:8080/rest-example/orders/list
 */

@Path("orders")
public class OrdersService
{
   public static Map<String, String> orders = new TreeMap<String, String>();

//   @Path("/{order}")
//   @POST
//   @Produces("text/html")
//   public String create(@PathParam("order") String order, @QueryParam("name") String customerName)
//   {
//      orders.put(order, customerName);
//      return "Added order #" + order;
//   }
   
   //Call met bv chrome simple rest client
   @Path("/{order}/{name}")
   @POST
   @Produces("text/html")
   public String createPost(@PathParam("order") String order, @PathParam("name") String naam)
   {
      orders.put(order, naam);
      return "<h2>Order POST</h2><p>Order " + order +" toegevoegd.";
   }
   
   @Path("/{order}/{name}")
   @GET
   @Produces("text/html")
   public String createGet(@PathParam("order") String order, @PathParam("name") String naam)
   {
      orders.put(order, naam);
      return "<h2>Order GET</h2><p>Order " + order +" toegevoegd.";
   }

   @Path("/{order}")
   @GET
   @Produces("text/html")
   public String find(@PathParam("order") String order)
   {
      if (orders.containsKey(order))
         return "<h2>Details on Order #" + order + "</h2><p>Customer name: " + orders.get(order);

      return "<h2>Details on Order #" + order + "</h2><p> Niet gevonden! ";
   }

   @Path("/list")
   @GET
   @Produces("text/html")
   public String list()
   {
      String header = "<h2>All Orders</h2>\n";

      header += "<ul>";
      for (Map.Entry<String, String> order : orders.entrySet())
         header += "\n<li>" + order.getKey() + " --> " + order.getValue() + "</li>";

      header += "\n</ul>";

      return header;
   }
}