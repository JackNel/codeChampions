(function() {
  'use strict';

  angular
    .module('message')
    .controller('MessageController', function($scope, $location, MessageService){
      var vm = this;

      vm.sendNewMessage = function(newMessage){
        MessageService.sendNewMessage(newMessage);
      };

      vm.sendReply = function(replyMessage){

        MessageService.sendReply(replyMessage);
      };
      vm.check = function(){
        console.log("in message controller");
        MessageService.check();
      };
      vm.getMessages = function(){
        MessageService.getMessages(1).then(function(res){
          vm.messages = res.data;});
    };
    vm.getMessages();
    });

}());
