using TicketReservationApp.Models.UserModel;

namespace TicketReservationApp.Services.UserService
{
    public interface IUserService
    {
        List<User> Get();
        User GetById(string id);
        User Create(User user);
        void Update(string id,User user);
        void Delete(string id);
    }
}
