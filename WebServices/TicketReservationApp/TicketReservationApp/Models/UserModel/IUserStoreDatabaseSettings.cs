﻿namespace TicketReservationApp.Models.UserModel
{
    public interface IUserStoreDatabaseSettings
    {
        string UserCollectionName { get; set; }
        string ConnectionString { get; set; }
        string DatabaseName { get; set; }
    }
}
