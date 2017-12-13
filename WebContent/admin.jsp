<%@page import="java.util.Iterator"%>
  <%@page import="entities.User"%>
    <%@page import="java.util.List"%>
      <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
        
        
          <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
          <title>Insert title here</title>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">

          <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

          <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
          <!-- Compiled and minified JavaScript -->
          <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>


          <style>
            html,
            body {
              padding: 0;
              margin: 0;
              display: flex;
              flex: 1 0 auto;
              flex-direction: column;
            }

            .content {
              display: flex;
              flex: 1 0 auto;
              flex-direction: column;
              align-items: center;
            }

            span.card-title {
              color: black;
            }

            .card {
              min-width: 80vw;
            }

            .divider {
              width: 80vw;
            }

            .modal-content {
              display: flex;
              flex-direction: column;
              align-items: center;
            }

            .modal-footer {
              display: flex;
              flex-direction: row;
              justify-content: center;
            }

            .section {
              display: flex;
            }

            .section div {
              padding: 0 20px;
            }

            .section div h4 {
              margin: 10px 0;
            }

            form {
              width: 100%;
            }
          </style>
          <% List<User> users = (List<User>)request.getAttribute("users"); %>

            <script src="https://cdn.jsdelivr.net/npm/vue"></script>



        </head>

        <body>
          <nav>
            <div class="nav-wrapper">
              <a href="#" class="brand-logo">Admin Page</a>
              <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li>
                  <a href="">Transaction</a>
                </li>
                <li>
                  <a href="">User</a>
                </li>
                <li>
                  <a href="">Category</a>
                </li>
              </ul>
            </div>
          </nav>
          <div class="content col s12" id="app">
            <div class="section">
              <div class="col m8">
                <h4>Users</h4>
              </div>
              <div class="col s4">
                <a class="btn-floating btn-large waves-effect waves-light red modal-trigger" href="#modal2" v-on:click="addModalInit()" >
                  <i class="material-icons">add</i>
                </a>
                <div id="modal2" class="modal">
                  <div class="modal-content">
                    <h4>Create User</h4>
                    <form class="col s12">
                      <div class="row">
                        <div class="input-field col s12">
                          <input id="user_name" type="text" class="validate" v-model="tmpUser.email">
                          <label for="user_name" >Email</label>
                        </div>
                      </div>
                      <div class="row">
                        <div class="input-field col s12">
                          <input id="password" type="password" class="validate" v-model="tmpUser.password">
                          <label for="password">Password</label>
                        </div>
                      </div>
                      <div class="row">
                        <div class="input-field col s12">
                          <input id="confirm_password" type="password" class="validate">
                          <label for="confirm_password">Confirm Password</label>
                        </div>
                      </div>
                    </form>
                  </div>
                  <div class="modal-footer">
                    <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat" v-on:click="addUser()">Save</a>
                    <a href="#!" class="modal-action modal-close waves-effect waves-red btn-flat">Cancel</a>
                  </div>
                </div>
              </div>
            </div>

            <div class="divider col s12"></div>
            <div class="card col s10" v-for="user in users">
              <div class="card-content white-text">
                <span class="card-title"> {{user.firstname}} {{user.lastname}} {{user.email}} {{user.username}}</span>
              </div>
              <div class="card-action">
                <a class="modal-trigger" href="#modal1" v-on:click="editModalInit(user)">Edit</a>
                <a class="modal-trigger" href="#modal" v-on:click="deleteModalInit(user)">Delete</a>
              </div>

            </div>

            <div id="modal1" class="modal">
              <div class="modal-content">
                <h4>Edit User</h4>
                <form class="col s12">
                  <div class="row">
                    <div class="input-field col s12">
                      <input id="first_name" type="text" class="validate" v-model="tmpUser.firstName">
                      <label for="first_name" >First Name</label>
                    </div>
                  </div>
                  <div class="row">
                    <div class="input-field col s12">
                      <input id="last_name" type="text" class="validate" v-model="tmpUser.lastName" >
                      <label for="last_name">Last Name</label>
                    </div>
                  </div>
                  <div class="row">
                    <div class="input-field col s12">
                      <input id="user_name" type="text" class="validate" v-model="tmpUser.userName" >
                      <label for="user_name">User Name</label>
                    </div>
                  </div>
                  <div class="row">
                    <div class="input-field col s12">
                      <input id="email" type="text" class="validate" v-model="tmpUser.email">
                      <label for="email">Email</label>
                    </div>
                  </div>
                </form>
              </div>
              <div class="modal-footer">
                <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat" v-on:click="updateUser()">Agree</a>
                <a href="#!" class="modal-action modal-close waves-effect waves-red btn-flat">Cancel</a>
              </div>
            </div>
            
            <div id="modal" class="modal">
              <div class="modal-content">
                <h4>Delete User</h4>
                <p>Are You Sure?</p>
              </div>
              <div class="modal-footer">
                <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat" v-on:click="deleteUser()">Agree</a>
                <a href="#!" class="modal-action modal-close waves-effect waves-red btn-flat">Cancel</a>
              </div>
            </div>


          </div>

          <script>
            $(document).ready(function () {
              // the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
              $('.modal').modal();
            });
          </script>


          <script>
          
          	var emptyUser = {
          			index: -1,
          			id: '',
          			firstName: '',
                    lastName: '',
                    email: '',
                    userName: '',
                    password: ''
                   
          	}
          
            var app = new Vue({
              el: '#app',
              data: {
                users: [],
                tmpUser: jQuery.extend({}, emptyUser)

              },
              methods: {
                
            	addModalInit:function(){
            		this.tmpUser = jQuery.extend({}, emptyUser);            		
            	},
                addUser: function () {
                	var shouldBeAdded = jQuery.extend({}, this.tmpUser); 
                 	//TODO REST add
                 	var selfUsers = this.users
					jQuery.ajax({
				        'type': 'POST',
				        'url': "http://localhost:8080/BudgetTracker/api/admin/addUser",
				        'contentType': 'application/json',
				        'data': JSON.stringify({ email: shouldBeAdded.email, password:shouldBeAdded.email}),
				        'dataType': 'json',
				        'success': function( data ) {
							 shouldBeAdded.id = data.id
							 shouldBeAdded.index = selfUsers.length;
							 selfUsers.push(shouldBeAdded)                 
			                 console.log('added')
						}
				    });
                 	                 	
                },                
                editModalInit: function(user) {
                	this.tmpUser.userName = user.username
                	this.tmpUser.firstName = user.firstname
                	this.tmpUser.lastName = user.lastname
                	this.tmpUser.email = user.email
                	this.tmpUser.index = user.index
                	this.tmpUser.id = user.id
                },   
                updateUser: function () {                       	
                	this.users[this.tmpUser.index].id = this.tmpUser.id;
                    this.users[this.tmpUser.index].email = this.tmpUser.email
                    this.users[this.tmpUser.index].firstname = this.tmpUser.firstName
                    this.users[this.tmpUser.index].lastname = this.tmpUser.lastName      
                    this.users[this.tmpUser.index].username = this.tmpUser.userName
                    var data = jQuery.extend({}, this.users[this.tmpUser.index]); 
                    delete data.index                    
                    console.log(JSON.stringify(data));
                    
                    jQuery.ajax({
				        'type': 'POST',
				        'url': "http://localhost:8080/BudgetTracker/api/admin/updateUser",
				        'contentType': 'application/json',
				        'data': JSON.stringify(data),
				        'dataType': 'json',
				        'success': function( data ) {
				        	console.log(data)						
						}
				    });	
				},
                deleteModalInit: function(user){
                	this.tmpUser.index = user.index
                },
				deleteUser:function(){
					var shouldBeDeleted = this.users[this.tmpUser.index];			
					var selfUsers = this.users
					
					jQuery.ajax({
				        'type': 'POST',
				        'url': "http://localhost:8080/BudgetTracker/api/admin/deleteUser",
				        'contentType': 'application/json',
				        'data': JSON.stringify({ id: shouldBeDeleted.id}),
				        'dataType': 'json',
				        'success': function( data ) {
				        	console.log(data)
				        	selfUsers.splice(shouldBeDeleted.index, 1);
							for(var i = 0; i < selfUsers.length;i++) {
								selfUsers[i].index = i;
							}
							
							//better here to close modal
						}
				    });																		
                }
              }
            })			          
          	
            <%int i = 0;%>
            <c:forEach items="${requestScope.users}" var="u">
            app.users.push({
              index: <%= i++ %>,
              id: '<c:out value="${u.getId() == null ? '' : u.getId()}"></c:out>',
              email: '<c:out value="${u.getEmail() == null ? '' : u.getEmail()}"></c:out>',
              firstname: '<c:out value="${u.getFirstName() == null ? '' : u.getFirstName()}"></c:out>',
              lastname: '<c:out value="${u.getLastName() == null ? '' : u.getLastName()}"></c:out>',
              username: '<c:out value="${u.getUserName() == null ? '' :u.getUserName()}"></c:out>',
            })
            
            </c:forEach>
            
          </script>

        </body>

        </html>