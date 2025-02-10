using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Bullet : MonoBehaviour
{
    private void OnCollisonEnter(Collision collision)
    {
        print(collision.collider);
        if (collision.gameObject.CompareTag("Target"))
        {
            MonoBehaviour.print("hit " + collision.gameObject.name + " !");
            Destroy(gameObject);
        }
    }
}
