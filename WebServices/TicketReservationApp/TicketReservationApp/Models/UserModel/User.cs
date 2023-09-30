﻿using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace TicketReservationApp.Models.UserModel
{
    public class User
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get; set; } = string.Empty;

        [BsonElement("nic")]
        public string NIC { get; set; } = string.Empty;

        [BsonElement("name")]
        public string Name { get; set; } = string.Empty;

        [BsonElement("email")]
        public string Email { get; set; } = string.Empty;

        [BsonElement("password")]
        public string Password { get; set; } = string.Empty;

        [BsonElement("role")]
        public string Role { get; set; } = string.Empty;
    }
}
