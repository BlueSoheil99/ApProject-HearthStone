package project01.Players;

public abstract class PlayerManagement {
    public static Player[] allPlayers;
    private static Player currentPlayer = null;
//    private static HashMap<String, String> usernamesANDpassword = new HashMap<>();

//setter
    public static void setAllPlayers(){
        //read jsons and make players within them . first get profile addresses from a specific .txt
    }

//    public void setUsernamesANDpassword(Player[] allPlayers) {
//        for (Player player: allPlayers) {
//            usernamesANDpassword.put(player.getUserName() , player.getPassword());
//        }
//    }

    public static void setCurrentPlayer(Player player){
        currentPlayer = player;
        //open this player's logfile

    } //must get username and password and then set currentPlayer

//getter
    public static Player getCurrentPlayer(){
        return currentPlayer;
    }





   public static boolean allPlayersContain(String username , String password){
        setAllPlayers();
//       if (usernamesANDpassword.get(username) == password){
//           return true;
//       }else {
//           return false;
//       }
       boolean ans = false;
       for (Player player: allPlayers) {
           if(player.getUserName().equals(username) && player.getPassword().equals(password)){
               ans = true;
               break;
           }
       }
       return ans;
   }

   public static Player getPlayer(String username , String password){
        Player player = null;
       for (Player x: allPlayers   ) {
           if (player.getUserName().equals(username) && player.getPassword().equals(password)){
               player = x;
           }
       }
       return player;
    }




//    public static void exit(){
//        if (currentPlayer != null ){
//            currentPlayer = null;
//        }else{
//            System.out.println("");
//        }
//    }
//
//    public static void exitAll(){}
//
//
//
//    public static void deletePlayer(String enteredPassword){
//        if (currentPlayer.getPassword().equals(enteredPassword)){
//            //delete profile
//            //add a DELETE log
//
//            exit();
//        }               //if password is correct we add a line in its log
//    }

}
