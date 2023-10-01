using Microsoft.AspNetCore.Mvc;
using TicketReservationApp.Models.UserModel;
using TicketReservationApp.Services.UserService;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace TicketReservationApp.Controllers.UserController
{
    [Route("api/[controller]")]
    [ApiController]
    public class UsersController : ControllerBase
    {
        private readonly IUserService userService;

        public UsersController(IUserService userService)
        {
            this.userService = userService;
        }

        // GET: api/<UsersController>
        [HttpGet]
        public ActionResult<List<User>> Get()
        {
            return userService.Get();
        }

        // GET api/<UsersController>/5
        [HttpGet("{id}")]
        public ActionResult<User> Get(string id)
        {
            var user = userService.Get(id);

            if (user == null)
            {
                return NotFound($"User with Id = {id} not found");
            }
            return user;
        }

        // POST api/<UsersController>
        [HttpPost]
        public ActionResult<User> Post([FromBody] User user)
        {
            userService.Create(user);
            return CreatedAtAction(nameof(Get), new { id = user.Id }, user);
        }

        // PUT api/<UsersController>/5
        [HttpPut("{id}")]
        public ActionResult Put(string id, [FromBody] User user)
        {
            var existingUser = userService.Get(id);
            if (existingUser == null)
            {
                return NotFound($"User with Id = {id} not found");
            }
            userService.Update(id, user);

            return NoContent();
        }

        // DELETE api/<UsersController>/5
        [HttpDelete("{id}")]
        public ActionResult Delete(string id)
        {
            var user = userService.Get(id);

            if (user == null)
            {
                return NotFound($"User with Id = {id} not found");
            }

            userService.Remove(user.Id);

            return Ok($"User with Id = {id} deleted");
        }
    }
}
